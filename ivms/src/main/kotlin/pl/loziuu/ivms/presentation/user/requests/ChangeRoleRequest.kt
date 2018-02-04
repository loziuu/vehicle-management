package pl.loziuu.ivms.presentation.user.requests

import pl.loziuu.ivms.identity.user.domain.Role

data class ChangeRoleRequest(val role: Role = Role.MANAGER)