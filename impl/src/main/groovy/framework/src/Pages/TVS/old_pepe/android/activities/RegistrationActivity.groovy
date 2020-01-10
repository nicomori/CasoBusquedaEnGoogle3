package framework.src.Pages.TVS.old_pepe.android.activities

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

/**
 * Created by pvh on 13/12/18
 */
class RegistrationActivity extends AndroidBaseActivity {

    static content = {
        pageTitle { $("#title").text() }
        continueButton { $('id':'button_next')}

        overEighteen { $('id':'radio_button_over_eighteen_text')}

        isStudent { $('id':'radio_button_is_student_text')}

        listNation { $('id':'nationality_placeholder_background')}
        nationality { $('id':'champi_picker_item_text')}
        nationalityPicker { $('id':'champi_picker_selector')}

        listCity { $('id':'city_placeholder_background')}

        userName{ $('id':'edit_text_name')}
        userSurname{ $('id':'edit_text_surname')}
        userEmail{ $('id':'registration_email_field')}
        userPassword{ $('id':'registration_password_field')}
        userTerms{ $('id':'registration_terms_checkbox')}


    }

    String getActivityName() {
        return "zenjob.android.feature.registration.RegistrationActivity";
    }

    void clickContinue(){
        continueButton.click()
    }

    void checkEighteen(){
        overEighteen.click()
    }

    void checkStudent(){
        isStudent.click()
    }

    void clickListNation(){
        listNation.click()
    }

    void setNationality(int index){
        //should be improved
        nationality[index].click()
    }

    void clickListCity(){
        listCity.click()
    }

    void setCity(int index){
        //should be improved
        nationality[index].click()
    }

    void setNames(String name, String surname){
        userName<<name
        userSurname<<surname
    }
    void checkTerms(){
        userTerms.click()
    }

    void setLogin(String email, String password){
        userEmail<<email
        userPassword<<password
    }

}
