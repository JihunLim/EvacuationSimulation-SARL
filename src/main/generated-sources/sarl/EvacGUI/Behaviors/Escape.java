package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;

@SarlSpecification("0.5")
@SarlElementType(17)
@SuppressWarnings("all")
public interface Escape extends Capacity {
  public static class ContextAwareCapacityWrapper<C extends Escape> extends Capacity.ContextAwareCapacityWrapper<C> implements Escape {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
  }
}
