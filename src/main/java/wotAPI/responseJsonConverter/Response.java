package wotAPI.responseJsonConverter;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class Response<T> {

    @SerializedName(value = "status")
    private String id;

    @SerializedName(value = "meta")
    //TODO Implement meta data
    private Object meta;

    @SerializedName(value = "data")
    List<T> data;

    public Response(String id, Object meta, List<T> data) {
        this.id = id;
        this.meta = meta;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Object getMeta() {
        return meta;
    }

    public List<T> getData(){
        return data;
    }

    @Override
    public String toString() {
        return "ResponseJsonConverter.Response{" +
                "id='" + id + '\'' +
                ", meta=" + meta +
                ", data=" + data +
                '}';
    }
}
