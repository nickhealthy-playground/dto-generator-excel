import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * DATADTO
 */
@Getter
@Setter
@ToString
public class DATADTO {

    private String DATA;				// 데이터
    private String DATA;				// 데이터
    private String DATA;

	@Override
	public HashMap<String, Integer> getFieldsLen() {
		Map<String, Integer> itemLens = new HashMap<>();

		itemLens.put("DATA", 1);		// 데이터
		itemLens.put("DATA", 2);		// 데이터
		itemLens.put("DATA", 3);
		return itemLens;
	}

	@Override
	public int getAllItemLen() {
		int iAllItemLen = 0;
		Map<String, Integer> itemLens = getFieldsLen();
		for (Map.Entry<String, Integer> entry: itemLens.entrySet()) {
			iAllItemLen += entry.getValue();
		}
		return iAllItemLen;
	}

}
