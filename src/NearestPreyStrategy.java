public class NearestPreyStrategy {
    import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class NearestPreyStrategy implements TargetingStrategy {
    @Override
    public Optional<Prey> selectTarget(Predator predator, List<Prey> prey) {
        return prey.stream().min(Comparator.comparingLong(candidate -> {
            long dx = (long) predator.getX() - candidate.getX();
            long dy = (long) predator.getY() - candidate.getY();
            return dx * dx + dy * dy;
        }));
    }
}

}
