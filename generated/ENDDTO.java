import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * ENDDTO
 */
@Getter
@Setter
@ToString
public class ENDDTO {

    private String 1;				// 1
    private String 2;				// 2
    private String 3;				// 3

	@Override
	public HashMap<String, Integer> getFieldsLen() {
		Map<String, Integer> itemLens = new HashMap<>();

		itemLens.put("1", 1);		// 1
		itemLens.put("2", 2);		// 2
		itemLens.put("3", 3);		// 3
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
