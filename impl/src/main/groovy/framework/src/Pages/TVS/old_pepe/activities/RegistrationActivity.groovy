package framework.src.Pages.TVS.old_pepe.activities

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

/**
 * Created by pvh on 13/12/18
 */
class RegistrationActivity extends AndroidBaseActivity {

    static content = {
        pageTitle { $("#title").text() }
        continueButton { $('#button_next')}

        overEighteen { $('#radio_button_over_eighteen_text')}

        isStudent { $('#radio_button_is_student_text')}

        listNation { $('#nationality_placeholder_background')}
        nationality { $('#champi_picker_item_text')}
        nationalityPicker { $('#champi_picker_selector')}

        listCity { $('#city_placeholder_background')}

        userName{ $('#edit_text_name')}
        userSurname{ $('#edit_text_surname')}
        userEmail{ $('#registration_email_field')}
        userPassword{ $('#registration_password_field')}
        userTerms{ $('#registration_terms_checkbox')}


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
        //nationalityPicker = natio
        nationality[index].click()
        //nationalityPicker[4].click()
    }

    void clickListCity(){
        listCity.click()
    }

    void setCity(int index){
        //nationalityPicker = natio
        nationality[index].click()
        //nationalityPicker[4].click()
    }

    void setNames(String name, String surname){
        userName=name
        userSurname=surname
    }
    void checkTerms(){
        userTerms.click()
    }

    void setLogin(String email, String password){
        userEmail=email
        userPassword=password
    }

}
