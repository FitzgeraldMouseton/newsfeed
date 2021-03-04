$(document).ready(function () {
    get_categories();
    get_news();

    $("#category-to-show").on('change', function () {
        get_news_by_category();
    })
})

function fill_story_html(id, title, text, time) {
    return '<div class="card border-primary mb-3" style="width: 100rem;">\n' +
        '         <div class="card-body" data-id="' + id + '">\n' +
        '             <h5 class="card-title">' + title + '</h5>\n' +
        '             <p class="card-text">' + text + '</p>\n' +
        '             <div class="card-footer bg-transparent border-success" id="se">\n' +
        '               <a href="#" class="btn btn-primary" onclick="show_single_story_page(' + id + ')">Редактировать</a>\n' +
        '               <button class="btn btn-primary" onclick="delete_story(' + id + ')">Удалить</button>\n' +
        '               <button class="card-subtitle" style="align-self: end">' + time + '</button>\n' +
        '               </div>\n' +
        '         </div>\n' +
        '     </div>';
}

function show_single_story_page(story_id) {
    // let t = $('button[id^="r-button-"]').attr('id')
    window.location.href = "http://localhost:8080/story?id=" + story_id;
}

function get_news() {
    $.get('/news', function (data) {
        let news = '<div class="col-sm">';
        for (let i = 0; i < data.length; i++) {
            news += fill_story_html(data[i].id, data[i].title, data[i].text, data[i].time);
        }
        news += '</div>';
        $("#newsfeed").html(news);
    })
}

function get_news_by_category() {
    $.get('/news/category?category=' + $("#category-to-show").val(), function (data) {
        let news = '<div class="col-sm">';
        for (let i = 0; i < data.length; i++) {
            news += fill_story_html(data[i].id, data[i].title, data[i].text)
        }
        news += '</div>'
        $("#newsfeed").html(news)
    })
}

function get_news_by_query() {
    $.get('/news/search?query=' + $("#search-field").val(), function (data) {
        let news = '<div class="col-sm">';
        for (let i = 0; i < data.length; i++) {
            news += fill_story_html(data[i].id, data[i].title, data[i].text)
        }
        news += '</div>'
        $("#newsfeed").html(news)
    })
}

function get_categories() {
    $.get('/categories', function (data) {
        let s = '<select><option value="-1">Выберите категорию</option>';
        for (let i = 0; i < data.length; i++) {
            s += '<option value=' + data[i].name + '>' + data[i].name + '</option>';
        }
        s += '</select>';
        $("#category-for-story").html(s)
        $("#category-to-show").html(s)
    })
}

function add_story() {
    $.ajax({
        url: '/news/add',
        dataType: 'json',
        type: 'POST',
        cache: false,
        contentType: 'application/json',
        data: JSON.stringify({
            title: $("#add-story-title").val(),
            text: $("#add-story-text").val(),
            category: $('#category-for-story').val()
        }),
        success: function () {
            // $(".fade").remove();
            $("#add-story").hide();
            get_news();
        },
        error: function (e) {
            let errors = e.responseText;
            errors = errors.substring(1, errors.length - 1).split(',');
            $.each(errors, function (index, value) {
                alert(value.split(':')[1])
            })
        }
    })
}

function delete_story(story_id) {
    $.ajax({
        url: '/news/delete/' + story_id,
        dataType: 'json',
        type: 'DELETE',
        success: function () {
            get_news()
        }
    })
}