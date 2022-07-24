package wotAPI.responseJsonConverter;

import java.util.List;

public class ScheduledMatchesResponse extends Response<ScheduledMatch> {

    public ScheduledMatchesResponse(String id, Object meta, List<ScheduledMatch> data) {
        super(id, meta, data);
    }

}
