package introsde.assignment3.soap;

import introsde.assignment3.soap.model.Activity;
import introsde.assignment3.soap.model.Person;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment3.soap.University",
	serviceName="UniversityService")
public class UniversityImpl implements University {

	@Override
	public Person readPerson(long id) {
		System.out.println("---> Reading Person by id = "+id);
		Person p = Person.getPersonById(id);
		if (p!=null) {
			System.out.println("---> Found Person by id = "+id+" => "+p.getFirstName()+ " " +p.getLastname());
		} else {
			System.out.println("---> Didn't find any Person with  id = "+id);
		}
		return p;
	}

	@Override
	public List<Person> readPersonList() {
		return Person.getAll();
	}

	@Override
	public Person createPerson(Person person) {
		 Person newPerson = Person.savePerson(person);
		 return newPerson;
	}

	@Override
	public Person updatePerson(Person person) {
		Person updatedPerson = Person.updatePerson(person);
		return updatedPerson;
	}

	@Override
	public int deletePerson(long id) {
		Person p = Person.getPersonById(id);
		if (p!=null) {
			Person.removePerson(p);
			return 0;
		} else {
			return -1;
		}
	}
	
	
	@Override
	public List<Activity> readPersonPreferencesWithType(long id, String activity_type){
		Person person = Person.getPersonById(id);
		return person.getActivitiesWithType(activity_type);
	}
	
	@Override
	public List<Activity> readPreferences(){
		return Activity.getAll();
	}
	
	@Override
	public Activity readPersonPreferencesWithId(long id, long activityId) {
		Person person = Person.getPersonById(id);
		return person.getActivitiesWithId(activityId);
	}
	
	@Override
	public Activity savePersonPreferences(long id, Activity activity) {
		Person person = Person.getPersonById(id);
		return person.addActivity(activity);
	}
	
	@Override
	public Activity updatePersonPreferences(long id, Activity activity) {
		Person person = Person.getPersonById(id);
		return person.getActivitiesWithId(activity.getIdActivity()).updateActivity(activity);
	}

}
