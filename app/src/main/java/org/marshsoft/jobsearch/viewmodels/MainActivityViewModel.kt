package org.marshsoft.jobsearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel():ViewModel() {
    private val isAboutCompany = MutableLiveData<Boolean>(false)
    val isAboutCompanyCurrent: LiveData<Boolean> = isAboutCompany

    fun setIsAboutCompany(isAboutCompanyNewValue:Boolean) {
        isAboutCompany.value = isAboutCompanyNewValue
    }
}