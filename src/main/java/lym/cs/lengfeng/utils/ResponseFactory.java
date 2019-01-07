package lym.cs.lengfeng.utils;

public class ResponseFactory {

    private ResponseFactory(){
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(Constants.REQUEST_SUCCESS, Constants.SUCCESS, data);
    }

    public static Response success(int code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static Response fail(int code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> response(T data) {
        if (data != null) {
            return success(data);
        } else {
            return fail(Constants.REQUEST_FAIL, Constants.FAIL);
        }
    }
}
