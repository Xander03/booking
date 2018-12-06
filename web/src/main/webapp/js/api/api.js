export const get = (url, success, failed) => {
    $.ajax({
        method: 'GET',
        url: url + getLanguage(),
        headers: getHeaders(),
    }).done(function (data) {
        success(JSON.parse(data));
    }).fail(function (error) {
        failed(error.responseText);
    });
};

export const post = (url, data, success, failed) => {
    $.ajax({
        method: 'POST',
        url: url + getLanguage(),
        data: JSON.stringify(data),
        headers: getHeaders(),
    }).done(function (data) {
        success(data);
    }).fail(function (error) {
        failed(error.responseText);
    });
};

export const put = (url, data, success, failed) => {
    $.ajax({
        method: 'PUT',
        url: url + getLanguage(),
        data: JSON.stringify(data),
        headers: getHeaders(),
    }).done(function (data) {
        success(JSON.parse(data));
    }).fail(function (error) {
        failed(error.responseText);
    });
};

export const patch = (url, success, failed) => {
    $.ajax({
        method: 'PATCH',
        url: url + getLanguage(),
        headers: getHeaders(),
    }).done(function (data) {
        success(JSON.parse(data));
    }).fail(function (error) {
        failed(error.responseText);
    });
};

export const remove = (url, success, failed) => {
    $.ajax({
        method: 'DELETE',
        url: url + getLanguage(),
        headers: getHeaders(),
    }).done(function (data) {
        success(data);
    }).fail(function (error) {
        failed(error.responseText);
    });
};

const getHeaders = () => {
    let token = localStorage.getItem('user');
    token = token === null ? '' : token;
    return {'Token' : token.replace(/[\r\n]/g, '')};
};

const getLanguage = () => {
    let language = new URL(window.location.href).searchParams.get('language');
    if (language === null || language === '') {
        language = 'en';
    }
    return `?language=${language}`;
};