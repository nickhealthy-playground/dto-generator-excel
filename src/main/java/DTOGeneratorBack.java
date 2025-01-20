import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOGeneratorBack {
    private static final String[] DTO_TYPES = {"START", "HEADER", "DATA", "TAILER", "END"};
    private static final String OUTPUT_DIR = "generated/";
    
    public static void generateDTOsFromExcel(String excelPath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath))) {
            Sheet sheet = workbook.getSheetAt(0);
            
            // DTO 타입별로 필드 정보를 저장할 Map
            Map<String, List<FieldInfo>> dtoFieldsMap = new HashMap<>();
            
            // 엑셀 파일 읽기
            int maxRows = sheet.getLastRowNum();
            for (int rowNum = 1; rowNum <= maxRows; rowNum++) {  // 첫 번째 행은 헤더로 가정
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;
                
                // 각 DTO 타입별로 필드 정보 수집
                for (int i = 0; i < DTO_TYPES.length; i++) {
                    int fieldNameCol = i * 2;
                    int commentCol = fieldNameCol + 1;
                    
                    String fieldName = getCellValue(row.getCell(fieldNameCol));
                    String comment = getCellValue(row.getCell(commentCol));
                    
                    if (fieldName != null && !fieldName.trim().isEmpty()) {
                        dtoFieldsMap
                            .computeIfAbsent(DTO_TYPES[i], k -> new ArrayList<>())
                            .add(new FieldInfo(fieldName.trim(), comment));
                    }
                }
            }
            
            // DTO 클래스 생성
            new File(OUTPUT_DIR).mkdirs();
            for (Map.Entry<String, List<FieldInfo>> entry : dtoFieldsMap.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    generateDTOClass(entry.getKey(), entry.getValue());
                }
            }
        }
    }
    
    private static String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return null;
        }
    }
    
    private static void generateDTOClass(String dtoType, List<FieldInfo> fields) throws IOException {
        String className = dtoType + "DTO";
        StringBuilder sb = new StringBuilder();
        
        // 클래스 선언
        sb.append("import lombok.ToString;\n");
        sb.append("import lombok.Getter;\n");
        sb.append("import lombok.Setter;\n\n");
        sb.append("/**\n");
        sb.append(" * ").append(className).append("\n");
        sb.append(" */\n");
        sb.append("@Getter\n");
        sb.append("@Setter\n");
        sb.append("@ToString\n");
        sb.append("public class ").append(className).append(" {\n\n");
        
        // 필드 선언
        for (FieldInfo field : fields) {
            // 필드
            sb.append("    private String ").append(field.name).append(";");


            // 필드 주석
            if (field.comment != null && !field.comment.trim().isEmpty()) {
//                sb.append("    /**\n");
//                sb.append("     * ").append(field.comment).append("\n");
//                sb.append("     */\n");
                sb.append("\t\t\t\t// ").append(field.comment).append("\n");
            }
//              필드
//            sb.append("    private String ").append(field.name).append(";\n\n");
        }
        sb.append("\n");

        /**
         * 메서드
         */
        // getFieldsLen()
        sb.append("\t@Override\n");
        sb.append("\tpublic HashMap<String, Integer> getFieldsLen() {\n");
        sb.append("\t\tMap<String, Integer> itemLens = new HashMap<>();\n\n");
        for (FieldInfo field : fields) {
            sb.append("\t\titemLens.put(\"" + field.name + "\", 10);");

            if (field.comment != null && !field.comment.trim().isEmpty()) {
                sb.append("\t\t// ").append(field.comment).append("\n");
            }
        }
        sb.append("\t\treturn itemLens;\n");
        sb.append("\t}\n\n");

        // getAllItemLen()
        sb.append("\t@Override\n");
        sb.append("\tpublic int getAllItemLen() {\n");
        sb.append("\t\tint iAllItemLen = 0;\n");
        sb.append("\t\tMap<String, Integer> itemLens = getFieldsLen();\n");

        sb.append("\t\tfor (Map.Entry<String, Integer> entry: itemLens.entrySet()) {\n");
        sb.append("\t\t\tiAllItemLen += entry.getValue();\n");
        sb.append("\t\t}\n");

        sb.append("\t\treturn iAllItemLen;\n");
        sb.append("\t}\n\n");

        // CLASS 끝
        sb.append("}\n");
        
        // 파일 작성
        try (FileWriter writer = new FileWriter(OUTPUT_DIR + className + ".java")) {
            writer.write(sb.toString());
        }
    }
    
    private static class FieldInfo {
        String name;
        String comment;
        
        FieldInfo(String name, String comment) {
            this.name = name;
            this.comment = comment;
        }
    }
    
    public static void main(String[] args) {
        try {
            generateDTOsFromExcel("C:\\fnvbat\\dev\\input.xlsx");
            System.out.println("DTO classes generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}