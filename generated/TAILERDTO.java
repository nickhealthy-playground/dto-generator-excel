import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * TAILERDTO
 */
@Getter
@Setter
@ToString
public class TAILERDTO {

    private String TAILER;

	@Override
	public HashMap<String, Integer> getFieldsLen() {
		Map<String, Integer> itemLens = new HashMap<>();

		itemLens.put("TAILER", 3);
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
