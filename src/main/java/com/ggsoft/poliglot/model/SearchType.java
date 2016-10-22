package com.ggsoft.poliglot.model;

/**
 * Created by info on 19.4.16..
 */
public enum SearchType {
    TIME_VISIT_A(1), //according to time of the last visit, ascending
    SIMPLE(2), //according to time of the last visit, ascending
    TIME_VISIT_D(3), //according to time of the last visit, descending
    TIME_CREATE_A(4), //according to time of creation, ascending
    TIME_CREATE_D(5); //according to time of creation, descending

    private int value;

    SearchType(int value) {
        this.value = value;
    }

}
