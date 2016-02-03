'use strict';
//Object which stores content and language to look for

App.factory('WordSearchObject', function (){
    var savedObject = {};
    function get(){
        return savedObject;
    };

    function set(data){
        savedObject = data;
    };

    function getContent(){
        return savedObject.content;
    }

    function setContent(data){
        savedObject.content = data;
    }

    return {
        set: set,
        get: get,
        getContent : getContent,
        setContent : setContent
    };


});