'use strict';
/*
Simple directive for autocomplete on word from database
*/
App.directive('autoComplete', function(){
    return function postLink(scope, iElement, iAttrs) {

        $(function () {
          $(iElement).autocomplete({
              minLength : 1,
              delay : 500,

              //define callback to format results
              source : function(request, response) {
                  $.getJSON("/words/searchword_list", request,
                      function(result) {
                          //alert(request); //debug
                          response($.map(result, function(
                                  item) {
                              return {
                                  // following property gets displayed in drop down
                                  label : item.content,
                                  // following property gets entered in the textbox
                                  value : item.id
                              }

                          }));
                      });
              },

              //define select handler
              select : function(event, ui) {
                  if (ui.item) {
                      event.preventDefault();
                      $("#selected_ span").append(
                              '<a href=' + ui.item.id + ' class="btn-metadata2" target="_blank">'
                                      + ui.item.label + '</a>');
                      //$("#tagQuery").value = $("#tagQuery").defaultValue
                      // 									var defValue = $("#wordQuery").prop(
                      // 											'defaultValue');
                      $("#content").val(ui.item.label);
                      //$("#wordIdValue").val(ui.item.value);
                      $("#content").blur();
                      return false;
                  }
              },

              focus : function(event, ui) {
                  event.preventDefault();
                  $("#content").val(ui.item.label);
                  //$("#wordIdValue").val(ui.item.id);
                  return false;
              }
          });
        });
    }
});
