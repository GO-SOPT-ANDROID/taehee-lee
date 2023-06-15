package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.android.go.sopt.base.ID_PATTERN
import org.android.go.sopt.base.PW_PATTERN
import org.android.go.sopt.data.local.GoSoptSharedPreference
import org.android.go.sopt.domain.model.UserInfo
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.Event
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val goSoptSharedPreference: GoSoptSharedPreference,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isCompletedSignUp = MutableLiveData<Event<Boolean>>()
    val isCompletedSignUp: LiveData<Event<Boolean>> get() = _isCompletedSignUp

    private val _isCompletedSignIn = MutableLiveData<Event<Boolean>>()
    val isCompletedSignIn: LiveData<Event<Boolean>> get() = _isCompletedSignIn

    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val name = MutableStateFlow("")
    val specialty = MutableStateFlow("")

    private var _isAutoMode = MutableStateFlow(authRepository.getAutoMode())
    val isAutoMode = _isAutoMode.asStateFlow()

    fun postSignUpResult() {
        viewModelScope.launch {
            val isSuccessful =
                authRepository.signUp(id.value, password.value, name.value, specialty.value)
            _isCompletedSignUp.value = Event(isSuccessful)
        }
    }

    fun postSignInResult() {
        viewModelScope.launch {
            val isSuccessful = authRepository.signIn(id.value, password.value)
            if (isSuccessful) {
                authRepository.setAutoMode(true)
            }
            _isCompletedSignIn.value = Event(isSuccessful)
        }
    }

    val checkIdState = id.debounce(300L)
        .distinctUntilChanged()
        .map { if (it.isNullOrEmpty()) "empty" else if (it.matches(Regex(ID_PATTERN))) "valid" else "invalid" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val checkPasswordState = password.debounce(300L)
        .distinctUntilChanged()
        .map { if (it.isNullOrEmpty()) "empty" else if (it.matches(Regex(PW_PATTERN))) "valid" else "invalid" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val checkNameState = name.debounce(300L)
        .distinctUntilChanged()
        .map { if (it.isNullOrEmpty()) "empty" else if (it.isNotBlank()) "valid" else "invalid" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val checkSpecialtyState = specialty.debounce(300L)
        .distinctUntilChanged()
        .map { if (it.isNullOrEmpty()) "empty" else if (it.isNotBlank()) "valid" else "invalid" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val checkSignUpState = combine(
        id,
        password,
        name,
        specialty
    ) { idValue, passwordValue, nameValue, specialtyValue ->
        if (idValue.matches(Regex(ID_PATTERN)) && passwordValue.matches(Regex(PW_PATTERN)) && nameValue.isNotBlank() && specialtyValue.isNotBlank()) "valid" else "invalid"
    }
        .debounce(300L)
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setUserInfo(userInput: UserInfo) {
        goSoptSharedPreference.setUserInfo(userInput)
    }
}