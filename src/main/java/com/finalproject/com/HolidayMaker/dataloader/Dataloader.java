package com.finalproject.com.HolidayMaker.dataloader;
import com.finalproject.com.HolidayMaker.model.Activity;
import com.finalproject.com.HolidayMaker.model.Place;
import com.finalproject.com.HolidayMaker.model.User;
import com.finalproject.com.HolidayMaker.model.UserType;
import com.finalproject.com.HolidayMaker.repository.ActivityRepository;
import com.finalproject.com.HolidayMaker.repository.PlaceRepository;
import com.finalproject.com.HolidayMaker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;



@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<User> users = loadUserData();
        List<Place> places = createPlaceEntries();
        createActivityEntries(places, users);
    }

    private List<User> loadUserData() {
        if(userRepository.count() > 0) {
            return userRepository.findAll();
        }
        // Users
        List<User> users = Arrays.asList(
                new User("Ron", "Benson", "rontb22@gmail.com", "ronB", "hashed_password", UserType.BASIC),
                new User("Love", "Johnson", "lovedagher@gmail.com", "loveJ", "hashed_password2", UserType.PREMIUM),
                new User("Beatrice", "Hilton", "beahilton@gmail.com", "beaH", "hashed_password3", UserType.BASIC),
                new User("Lewis", "Mcdonald", "adipdagher@gmail.com", "LewisM", "hashed_password4", UserType.PREMIUM)
        );
        return userRepository.saveAll(users);
    }

    private List<Place> createPlaceEntries() {
        // Places setup
        List<Place> places = Arrays.asList(
                new Place("Central Park", "A large public park in New York City."),
                new Place("Golden Gate Park", "A large urban park in San Francisco."),
                new Place("Disneyland", "An amusement park in California."),
                new Place("Eiffel Tower", "A wrought iron lattice tower in Paris, France."),
                new Place("Colosseum", "A historical amphitheater located in the center of Rome, Italy."),
                new Place("Niagara Falls", "Famous waterfalls located on the border of USA and Canada."),
                new Place("Great Wall of China", "Ancient series of walls and fortifications located in northern China."),
                new Place("Santorini", "An island known for its stunning sunsets and whitewashed buildings in Greece."),
                new Place("Pyramids of Giza", "Iconic ancient Egyptian pyramids located in Cairo, Egypt."),
                new Place("Machu Picchu", "An Incan citadel set high in the Andes Mountains in Peru.")
        );
        return placeRepository.saveAll(places);
    }

    private void createActivityEntries(List<Place> places, List<User> users) {
        // Activities setup
        List<Activity> activities = Arrays.asList(
                new Activity("Picnic", "Have a picnic in the park.", places.get(0), users.get(0)),
                new Activity("Sightseeing", "Explore the Golden Gate Park.", places.get(1), users.get(1)),
                new Activity("Jet skiing", "Enjoy the view of Miami with our state of art jet skis", places.get(2), users.get(2)),
                new Activity("Language exchange", "Enjoy learning a new language and meeting new people on my Language exchange program", places.get(3), users.get(3)),
                new Activity("Gladiator Tour", "Explore the ancient ruins of the Roman Empire.", places.get(4), users.get(0)),
                new Activity("Waterfall Hike", "Experience the breathtaking views and sounds of Niagara Falls.", places.get(5), users.get(1)),
                new Activity("Wall Trek", "Trek along one of the world’s greatest wonders.", places.get(6), users.get(2)),
                new Activity("Sunset Viewing", "Watch a beautiful sunset in Santorini.", places.get(7), users.get(3))
        );
        activityRepository.saveAll(activities);
    }
}
