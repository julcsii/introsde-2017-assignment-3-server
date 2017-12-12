package introsde.assignment3.init;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import introsde.assignment3.soap.model.*;

public class DatabaseInit {

	public static List<Activity> createActivities(){
		List<Activity> activities = new ArrayList<Activity>();
		Activity a1 = new Activity("Running", "Running on the track", "Trento", ActivityType.SPORT,"2000-12-11");
		activities.add(a1);
		
		Activity a2 = new Activity("Cooking", "Japanese cooking class", "Rovereto", ActivityType.SOCIAL,"2007-12-21");
		activities.add(a2);
		
		Activity a3 = new Activity("Museum", "Attending history museum", "Budpest", ActivityType.CULTURE, "2017-12-11");
		activities.add(a3);
		
		Activity a4 = new Activity("Summer school", "EIT Digital summer school on Big Data", "Stockholm", ActivityType.ACADEMIC, "2016-05-04");
		activities.add(a4);
		
		Activity a5 = new Activity("Treasure Hunt", "Fun outdoors pub crawl with colleauges", "Budapest", ActivityType.WORK, "2003-07-18");
		activities.add(a5);
		
		Activity a6 = new Activity("Dog Wlaking", "Walking with the family dog", "Tata", ActivityType.SOCIAL, "2006-01-01");
		activities.add(a6);
		
		Activity a7 = new Activity("Yoga", "Relaxing and streching", "Budapest", ActivityType.SPORT, "2008-06-13");
		activities.add(a7);
		
		Activity a8 = new Activity("Hiking", "Going up to mountain peaks", "Trento", ActivityType.SPORT, "2015-02-14");
		activities.add(a8);
		
		Activity a9 = new Activity("Shopping", "Going to shopping centre", "Vienna", ActivityType.SOCIAL, "2014-09-11");
		activities.add(a9);
		
		Activity a10 = new Activity("Exam", "Proving results of studying in a semester", "Budapest", ActivityType.ACADEMIC, "2016-03-18");
		activities.add(a10);
		
		return activities;
	}
	
	public static List<Person> createFivePeople(){
		List<Person> people = new ArrayList<>();
		List<Activity> activityList = createActivities();
		Person p1 = new Person("Julia", "Hermann", "1993-05-04", activityList.subList(0, 2));
		people.add(p1);
		activityList.get(0).setPerson(p1);
		activityList.get(1).setPerson(p1);
		
		Person p2 = new Person("Daniella", "Kanyicska", "1993-02-10", activityList.subList(2, 4));
		people.add(p2);
		activityList.get(2).setPerson(p2);
		activityList.get(3).setPerson(p2);
		
		Person p3 = new Person("Zsofia", "Lele", "1992-09-10", activityList.subList(4, 6));
		people.add(p3);
		activityList.get(4).setPerson(p3);
		activityList.get(5).setPerson(p3);

		Person p4 = new Person("Kata", "Hermann", "1999-07-18", activityList.subList(8, 10));
		people.add(p4);
		activityList.get(8).setPerson(p4);
		activityList.get(9).setPerson(p4);

		Person p5 = new Person("Mate", "Mirko", "1991-03-21", activityList.subList(6, 8));
		people.add(p5);
		activityList.get(6).setPerson(p5);
		activityList.get(7).setPerson(p5);
		
		return people;
	}
	
	
	
	public static List<Person> init() {
		List<Person> people = new ArrayList<>();
		Person person;
		for (int i=0; i<5; i++) {
			person = Person.savePerson(createFivePeople().get(i));
			people.add(person);
		}
		return people;

		
	}
	
}
