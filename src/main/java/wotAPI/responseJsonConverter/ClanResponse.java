package wotAPI.responseJsonConverter;

import java.util.List;

public class ClanResponse extends Response<Clan> {
    public ClanResponse(String id, Object meta, List<Clan> data) {
        super(id, meta, data);
    }
}
