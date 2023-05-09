package org.android.go.sopt.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.android.go.sopt.base.ID_PATTERN
import org.android.go.sopt.base.PW_PATTERN

class SignUpViewModel : ViewModel() {

    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val name = MutableStateFlow("")
    val specialty = MutableStateFlow("")

    val checkSignUpState = combine(id, password) { idValue, passwordValue ->
        if (idValue.matches(Regex(ID_PATTERN)) && passwordValue.matches(Regex(PW_PATTERN))) "valid" else "invalid"
    }
        .debounce(300L)
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

}