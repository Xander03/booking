import {SIGN_IN, FORBIDDEN} from "../settings/URLs.js";
import {redirect} from "../utils/redirector.js";
import {getUser} from "../utils/local_storage.js";

const user = getUser();
if (user !== null) {
    if (!user.roles.includes('ADMIN')) {
        redirect(FORBIDDEN);
    }
} else {
    redirect(SIGN_IN);
}