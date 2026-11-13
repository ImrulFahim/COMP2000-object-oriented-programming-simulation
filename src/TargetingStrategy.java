public class TargetingStrategy {
    import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface TargetingStrategy {
    Optional<Prey> selectTarget(Predator predator, List<Prey> prey);
}
}
