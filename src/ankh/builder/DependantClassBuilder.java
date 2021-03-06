package ankh.builder;

/**
 *
 * @author Ankh Zet (ankhzet@gmail.com)
 * @param <Type> Class to produce
 * @param <Dependency> Class, builder is dependent of
 */
public class DependantClassBuilder<Type, Dependency> extends ClassBuilder<Type> {

  Dependency dependency;

  public DependantClassBuilder(Class<? extends Type> classRef, Dependency dependency) {
    super(classRef);
    this.dependency = dependency;
  }

  public Dependency getDependency() {
    return dependency;
  }

}
