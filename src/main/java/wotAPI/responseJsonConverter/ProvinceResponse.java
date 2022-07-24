package wotAPI.responseJsonConverter;

import java.util.List;

public class ProvinceResponse extends Response<ProvinceResponse> {
    public ProvinceResponse(String id, Object meta, List<ProvinceResponse> data) {
        super(id, meta, data);
    }
}
