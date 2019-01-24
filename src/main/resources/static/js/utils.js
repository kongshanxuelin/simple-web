var page_vars = {};

function create_table(selector, table_config){
    var table =	$(selector).on('xhr.dt', function ( e, settings, json, xhr ) {
        if(xhr.getResponseHeader("sessionStatus")=="timeout"){
            top.location.href="/login";
        }
    }).dataTable(table_config);
    return table;
}


function init_page(){
    set_context_path();
    setup_session_timeout_handler();
}

function set_context_path() {
    var context_path = $('meta[name=context-path]').attr("content");
    context_path = context_path.substr(0, context_path.length - 1);
    page_vars.context_path = context_path;
}

function setup_session_timeout_handler(){
    $.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
        if (!options.crossDomain) {
            options.url = page_vars.context_path + options.url;
        }
    });

    $(document).ajaxComplete(function(event, xhr, settings) {
        if(xhr.getResponseHeader("sessionStatus") == "timeout"){
            if(top){
                top.location.href = "/login";
            }
            else{
                location.href = "/login";
            }
        }
    });
}


