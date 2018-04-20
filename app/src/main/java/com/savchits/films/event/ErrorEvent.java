package com.savchits.films.event;

/**
 * Created by Zakhar Savchits on 20.04.2018.
 */
public class ErrorEvent {
    private Integer message;
    private Boolean loading;

    public ErrorEvent(Integer message, Boolean loading) {
        this.message = message;
        this.loading = loading;
    }

    public Integer getMessage() {
        return message;
    }

    public Boolean getLoading() {
        return loading;
    }
}
