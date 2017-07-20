package EvacGUI.Agent;

import EvacGUI.Behaviors.SimulStart;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SarlElementType(16)
@SuppressWarnings("all")
public class human_child_female extends Agent {
  private int life = 80;
  
  private int strength = 15;
  
  private int age;
  
  private String sex;
  
  private int emotion;
  
  @SyntheticMember
  private void $behaviorUnit$SimulStart$0(final SimulStart occurrence) {
    InputOutput.<String>println(("mode is " + Integer.valueOf(occurrence.mode)));
    UUID _iD = this.getID();
    String _plus = ("agent which is " + _iD);
    String _plus_1 = (_plus + " is spawned!");
    InputOutput.<String>println(_plus_1);
  }
  
  @SyntheticMember
  @Pure
  private boolean $behaviorUnitGuard$SimulStart$0(final SimulStart it, final SimulStart occurrence) {
    return (occurrence.mode == 1);
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SimulStart(final SimulStart occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    if ($behaviorUnitGuard$SimulStart$0(occurrence, occurrence)) {
      ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SimulStart$0(occurrence));
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    human_child_female other = (human_child_female) obj;
    if (other.life != this.life)
      return false;
    if (other.strength != this.strength)
      return false;
    if (other.age != this.age)
      return false;
    if (!Objects.equals(this.sex, other.sex)) {
      return false;
    }
    if (other.emotion != this.emotion)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.life;
    result = prime * result + this.strength;
    result = prime * result + this.age;
    result = prime * result + Objects.hashCode(this.sex);
    result = prime * result + this.emotion;
    return result;
  }
  
  @SyntheticMember
  public human_child_female(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public human_child_female(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
}
