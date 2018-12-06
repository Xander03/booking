import {redirect, goBack} from "../utils/redirector.js";
import {SIGN_IN, HOME, HOTEL_LIST} from "../settings/URLs.js";
import {getUser} from "../utils/local_storage.js";

$(document).ready(function () {
    $('#sign_out').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        localStorage.removeItem('user');
        redirect(SIGN_IN);
        return false;
    });
    if (getUser() === null || getUser() === undefined) {
        $('#nav-bar').css('display', 'none');
    }
    $('#main_page').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        redirect(HOME);
        return false;
    });
    $('#hotels_page').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        redirect(HOTEL_LIST);
        return false;
    });
    $('#back').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        goBack();
        return false;
    })
});