package framework.src.Pages.ZVS.company

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser

class CompanyDetailsPage extends BasePage {

    static url = 'https://staging-main.zenjob.org/ops/company/show'

    static content = {

        form { $(By.xpath('//form')) }
        submitButton { $(By.xpath('//input[@type="submit"]')) }
        Companyalretmsg { $(By.xpath("//div[@class='alert alert-danger']")) }
        //contractstable  { $("table") }
        contractstable { $(By.xpath('//div[@class=\'panel panel-default panel-danger\']//div[contains(@class,\'table-responsive\')]')) }
        Actcontractstable { $(By.xpath('//div[@class=\'panel panel-default panel-info\']//div[@class=\'panel-body\']')) }
        Locationstable { $(By.xpath('//div[contains(@class, \'panel \') and contains(.//h3[@class=\'panel-title\']//text(), \'Locations\')]')) }
        Locationstable1 {$(By.xpath("//div[@class=\"panel panel-default\"]//tbody[@class=\"panel-body\"]"))}
        inactivetableRows { contractstable.$('tbody > tr').moduleList(InactiveContractsRow) }
        activetableRows { Actcontractstable.$('tbody > tr').moduleList(ActiveContractsRow) }
        LocationRows { Locationstable1.$('tbody > tr').moduleList(LocationRow) }
        locationLongitude { $(By.id('locationLongitude')) }
        locationLatitude { $(By.id('locationLatitude')) }
        jobLocationCountry { $(By.xpath('//input[@id=\'map-search-input\']')) }
        jobMapLocation { $(By.id('map-search-input')) }
        LocationCountry { $(By.id('country')) }
        CompanyNameLink { $(By.xpath('//a[contains(text(),"company")]')) }

        panel { String panelTitle ->
            $(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), '${panelTitle}')]"))
        }

        Contractpanel { String panelTitle ->
            $(By.xpath("//div[contains(text(),'${panelTitle}')]//ancestor::div[@class='col-lg-9']"))
        }


        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath(".//a[normalize-space()='${text}']"))
        }

        // for most of the panels
        editButton { Navigator panel ->
            linkWithText(panel, 'Edit')
        }


        // for most of the panels
        AddButton { Navigator panel ->
            linkWithText(panel, 'Add')
        }

        // for contracts panel
        addNewButton { Navigator panel ->
            linkWithText(panel, 'Add New')
        }

        Generaldetailsval { Navigator nav , int rowno ->
            nav.find(By.xpath("*//div[@class='col-lg-8']"))[rowno].text()
        }

        Generaldetailsfield { Navigator nav , int rowno ->
            nav.find(By.xpath("*//div[@class='col-lg-4 text-right']"))[rowno].text()
        }

        ActiveContract { Navigator nav, String text->
            assert nav.find(By.xpath("//div[contains(text(),'${text}')]"))
            assert nav.find(By.xpath("//a[contains(text(),'Details')]"))
            // assert nav.find(By.xpath("//th//a[2]"))
        }

    }

    void checkmsg() {
        assert $(Companyalretmsg).text().contains('"name" may not be empty.')
    }

    void ContractAlertMsg() {
        assert $(Companyalretmsg).text().contains('"ranuvId" may not be empty.')
        assert $(Companyalretmsg).text().contains('"signingDate" may not be empty.')
    }

    void CreateJObLocationAlert(Map<String, String> data)
    {
        data.each { String alertmsg, String msgchk ->
            assert $(Companyalretmsg).text().contains(alertmsg)
        }

    }

    def activatedtext(int rowno)
    {
        String extractedvalues=Generaldetailsval(panel('General Details'),rowno)
        String extractedheading=Generaldetailsfield(panel('General Details'),rowno)
        extractedheading=extractedheading.replace(" ","")
        String emptext=extractedheading.toLowerCase()+","+extractedvalues
        return emptext
    }



    void editGeneralDetails() {
        editButton(panel('General Details')).click()
    }

    void AddLocationDetails() {
        AddButton(panel('Locations')).click()
    }

    void AddContract() {
        addNewButton(panel('Contract')).click()
    }

    void ClickCompanyName()
    {
        CompanyNameLink.click()
    }

    void fillFields(Map<String, String> data)
    {
        data.each { String fieldName, String fieldValue ->

            Date date = new Date()
            def formattedDate = date.format("dd/MM/yyy")
            //println(formattedDate)
            String datePart = date[Calendar.YEAR].toString()+date[Calendar.MONTH].toString()+date[Calendar.DAY_OF_MONTH].toString()+date[Calendar.HOUR].toString()+date[Calendar.MINUTE].toString()
            if("${fieldName}"=="paymentTermAmount" || "${fieldName}"=="paymentTermUnit" || "${fieldName}"=="invoicingType" || "${fieldName}"=="stream" || "${fieldName}"=="businessRegion" || "${fieldName}"=="locationname" || "${fieldName}"=="signingDate")
            {
                fieldValue = fieldValue

                if("${fieldName}"=="signingDate")
                    fieldValue=formattedDate
            }
            else
            {
                if(fieldValue.length()>0)
                    fieldValue=fieldValue+datePart
            }


            if("${fieldName}"=="businessRegion")
            {
                if(fieldValue.length()>0)
                {
                    $(By.xpath('//select[@id=\'businessRegion\']')).click()
                    $(By.xpath('//select[@id=\'businessRegion\']')).find("option").find{ it.value() == "BERLIN" }.click()
                }

            }
            else if("${fieldName}"=="locationname")
            {
                jobLocationCountry.click()
                jobLocationCountry.value(fieldValue)
                sleep(2000)

                waitFor {
                    locationLongitude && locationLatitude
                }
                jobLocationCountry << Keys.ARROW_DOWN
                sleep(2000)
                LocationCountry.click()
                sleep(2000)
                jobLocationCountry.click()
                sleep(8000)
            }
            else
            {
                form."${fieldName}" = parseFieldValue(fieldValue)
            }

        }

    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void submit() {
        submitButton.click()
    }




    void CheckLocationName(def data) {

        String LocationChk="Location name Not Displayed under Locations panel"

        data.each { def fieldName, def fieldValue ->
            Date date = new Date()
            String datePart = date[Calendar.YEAR].toString()+date[Calendar.MONTH].toString()+date[Calendar.DAY_OF_MONTH].toString()+date[Calendar.HOUR].toString()
            fieldValue=fieldValue+datePart
            for(int row=0;row<LocationRows.size();row++)
            {
                //println(LocationRows[row]."${fieldName}")
                //println(fieldValue)
                if(LocationRows[row]."${fieldName}".contains(fieldValue))
                {
                    LocationChk="Location name Displayed under Locations panel"
                    assert LocationRows[row].edit.size()==1
                    assert LocationRows[row].show.size()==1
                }
            }

        }

        assert LocationChk == "Location name Displayed under Locations panel"
    }

    void CheckGenUpdatedDetails(Map<String, String> data)
    {
        int k=0
        data.each { String fieldName, String fieldValue ->
            Date date = new Date()
            String datePart = date[Calendar.YEAR].toString() + date[Calendar.MONTH].toString() + date[Calendar.DAY_OF_MONTH].toString() + date[Calendar.HOUR].toString()
            String Expectedtext
            if("${fieldName}"=="Paymentterm" || "${fieldName}"=="invoicingType" || "${fieldName}"=="stream")
            {
                Expectedtext = fieldValue
            }
            else
            {
                Expectedtext=fieldValue+datePart
            }

            for ( int j=k; ; j++)
            {

                if (activatedtext(j).toLowerCase().contains("${fieldName}".toLowerCase()))
                {
                    assert activatedtext(j).toLowerCase().contains(Expectedtext.toLowerCase())
                    break
                }

            }
            k++
        }

    }
}

class InactiveContractsRow extends Module {
    static content = {
        cell { $("td") }
        ranuvid { cell[0].text() }
        signdate { cell[1].text() }
        createdDate { cell[2].text() }
        createdby { cell[3].text() }
        activate { cell[4].$("a")[0] }
        details {cell[4].$("a")[1]}
    }
}


class ActiveContractsRow extends Module {
    static content = {
        cell { $("td") }
        ranuvidlabel { cell[0].text() }
        ranuvid { cell[1].text() }
        signdatelabel { cell[2].text() }
        signdate { cell[3].text() }
    }
}


class LocationRow extends Module {
    static content = {
        cell { $("td") }
        locationname { cell[0].text() }
        edit { cell[1].$("a")[0] }
        show {cell[1].$("a")[1]}
    }
}

