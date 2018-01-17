package pl.loziuu.ivms.presentation.user.requests

data class NewUserRequest(val login: String = "",
                          val password: String = "")