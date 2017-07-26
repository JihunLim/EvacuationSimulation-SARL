package EvacGUI.Behaviors;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SarlSpecification("0.5")
@SarlElementType(13)
@SuppressWarnings("all")
public class A_Dead extends Event {
  @SyntheticMember
  public A_Dead() {
    super();
  }
  
  @SyntheticMember
  public A_Dead(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
