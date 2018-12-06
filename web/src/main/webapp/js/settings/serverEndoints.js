const HOST = 'http://localhost:8000/api';

const USER = HOST + '/users';
const ROOM = HOST + '/rooms';
const HOTEL = HOST + '/hotels';

export const SIGN_IN_URL = USER + '/signIn';
export const SIGN_UP_URL = USER;

export const ROOM_URL = ROOM;
export const ROOM_FREE_URL = ROOM_URL + '/free';
export const GET_RESERVED_ROOMS = ROOM_URL + '/reserved';

export const HOTEL_URL = HOTEL;
