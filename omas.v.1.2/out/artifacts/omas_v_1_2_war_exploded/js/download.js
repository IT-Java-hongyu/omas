// 文件下载
$.download = function(url){    // 获得url
       
    
    $('<form action="'+ url +'" method="post"></form>')
    .appendTo('body').submit().remove();        
};