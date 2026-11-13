public class OldestPreyStrategy {
    import java.util.List;
import java.util.Optional;

public class OldestPreyStrategy implements TargetingStrategy {
    @Override
    public Optional<Prey> selectTarget(Predator predator, List<Prey> prey) {
        // Prey are appended on arrival, so index 0 is the earliest survivor.
        return prey.isEmpty() ? Optional.empty() : Optional.of(prey.get(0));
    }
}
}
