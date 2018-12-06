import {HOME, SIGN_IN} from "../settings/URLs.js";
import {redirect} from "../utils/redirector.js";

const USER = 'user';

const user = localStorage.getItem(USER);
if (user !== null && user !== '') {
    if (window.location.pathname !== HOME) {
        redirect(HOME);
    }
} else {
    redirect(SIGN_IN);
}