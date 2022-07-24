package wotAPI.responseJsonConverter;

import java.util.List;

public class Province extends Response<Province> {
    public Province(String id, Object meta, List<Province> data) {
        super(id, meta, data);
    }
}
