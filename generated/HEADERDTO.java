import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * HEADERDTO
 */
@Getter
@Setter
@ToString
public class HEADERDTO {

    private String HEADER;				// 헤더
    private String HEADER;				// 헤더

	@Override
	public HashMap<String, Integer> getFieldsLen() {
		Map<String, Integer> itemLens = new HashMap<>();

		itemLens.put("HEADER", 1);		// 헤더
		itemLens.put("HEADER", 2);		// 헤더
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
