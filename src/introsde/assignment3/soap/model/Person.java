package introsde.assignment3.soap.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import introsde.assignment3.dao.UniversityDao;

/**
 * The persistent class for the "Person" database table.
 * 
 */
@Entity
@Table(name="Person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@XmlRootElement(name="person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idPerson;
	
	@Column(name="firstname")
	private String firstname;

	@Column(name="lastname")
	private String lastname;

	@Column(name="birthdate")
	private String birthdate;
	
	
	@OneToMany(mappedBy="person",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	//@XmlElementWrapper(name="preferences")
	private List<Activity> activitypreference;
	
	public Person() {
	}
	

	public Person(String firstname, String lastname, String birthdate, List<Activity> activitypreference) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.activitypreference = activitypreference;
	}
	
	
	public Person(String firstname, String lastname, String birthdate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", firstname=" + firstname + ", lastname=" + lastname + ", birthdate="
				+ birthdate + ", activitypreference=" + activitypreference + "]";
	}


	
	public long getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(long idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstName() {
		return this.firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}


	//@XmlTransient
	public List<Activity> getActivityPreferences() {
	    return this.activitypreference;
	}
	
	public void setActivityPreferences(List<Activity> activitypreference) {
	    this.activitypreference = activitypreference;
	}

	public List<Activity> getActivitiesWithType(String type) {
	    List<Activity> activities = getActivityPreferences();
	    List<Activity> activitiesWithType = new ArrayList<>();
	    
	    for (Activity activity : activities) {
	    	if (activity.getType().getActivityType().equals(type)) {
	    		activitiesWithType.add(activity);
	    	}
	    }
	    return activitiesWithType;
	}
	
	
	public static Person getPersonById(long id) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		Person p = em.find(Person.class, id);
		UniversityDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Person> getAll() {
		System.out.println("--> Initializing Entity manager...");
		EntityManager em = UniversityDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
		UniversityDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Person savePerson(Person p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		
		for(int i=0;i<p.activitypreference.size();i++) {
			em.persist(p.activitypreference.get(i));
		}
		
		tx.commit();
		UniversityDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Person updatePerson(Person p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
		UniversityDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removePerson(Person p) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    UniversityDao.instance.closeConnections(em);
	}

	
	public Activity getActivitiesWithId(long activityId) {
		List<Activity> activities = getActivityPreferences();
	    
	    for (Activity activity : activities) {
	    	if (activity.getIdActivity()==activityId) {
	    		return activity;
	    	}
	    }
	   return null;
	}


	public Activity addActivity(Activity activity) {
		EntityManager em = UniversityDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		List<Activity> origActivities = this.getActivityPreferences();
		origActivities.add(activity);
		this.setActivityPreferences(origActivities);
		Activity.saveActivity(activity);
		em.merge(this);
		tx.commit();
	    UniversityDao.instance.closeConnections(em);
		return activity;
		
	}


	public List<Activity> getBestPreferences() {
		List<Activity> activities = getActivityPreferences();
	    Stack<Activity> stack = new Stack();
	    stack.push(activities.get(0));
	    for (int i =1; i< activities.size();i++) {
	    	if (activities.get(i).getRating() >= stack.peek().getRating()){
	    		stack.pop();
	    		stack.push(activities.get(i));
	    	}
	    }
	   return stack;
	}



}

