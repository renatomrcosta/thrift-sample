namespace java com.xunfos.playground.thrift

struct User {
    1: string id
    2: string name
}

struct GetUserRequest {
    1: required string id
}

struct GetUserResponse {
    1: User user
}

struct GetUsersResponse {
    1: list<User> users
}

struct PingResponse {
    1: bool success
}

service PlaygroundService {
    PingResponse ping()
    GetUserResponse getUser(1: required GetUserRequest request)
    GetUsersResponse getUsers()
}
