package wotAPI.responseJsonConverter;

import java.util.List;

public class ProvinceResponse extends Response<Province> {
    public ProvinceResponse(String id, Object meta, List<Province> data) {
        super(id, meta, data);
    }
}
