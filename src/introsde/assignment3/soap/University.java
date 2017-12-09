package introsde.assignment3.soap;
import introsde.assignment3.soap.model.Activity;
import introsde.assignment3.soap.model.Person;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface University {
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") long id);
 
    @WebMethod(operationName="readPersonList")
    @WebResult(name="people") 
    public List<Person> readPersonList();
 
    @WebMethod(operationName="createPerson")
    @WebResult(name="createdPerson") 
    public Person createPerson(@WebParam(name="person") Person person);
 
    @WebMethod(operationName="updatePerson")
    @WebResult(name="updatedPerson") 
    public Person updatePerson(@WebParam(name="person") Person person);
    
    @WebMethod(operationName="deletePerson") 
    public int deletePerson(@WebParam(name="personId") long id);
    
    @WebMethod(operationName="readPersonPreferencesWithType")
    @WebResult(name="personPreferencesWithType") 
    public List<Activity> readPersonPreferencesWithType(long id, String activity_type);
    
    @WebMethod(operationName="readPreferences")
    @WebResult(name="preferences") 
    public List<Activity> readPreferences();
    
    @WebMethod(operationName="readPersonPreferencesWithId")
    @WebResult(name="personPreferencesWithId") 
    public Activity readPersonPreferencesWithId(long id, long activityId);
    
    @WebMethod(operationName="savePersonPreferences")
    @WebResult(name="savedActivity") 
    public Activity savePersonPreferences(long id, Activity activity);
    
    @WebMethod(operationName="updatePersonPreferences")
    @WebResult(name="updatedActivity") 
    public Activity updatePersonPreferences(long id, Activity activity);
    
    /*

     * evaluatePersonPreferences(Long id, Activity activity, int value) => Preference 
     * getBestPersonPreference(Long id) => List<Preference> 
     */
    /*
    @WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
    */
}