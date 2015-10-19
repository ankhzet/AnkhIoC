package ankh.factory;

import ankh.builder.Builder;
import ankh.builder.ClassBuilder;
import ankh.resolver.DependencyResolver;

/**
 *
 * @author Ankh Zet (ankhzet@gmail.com)
 * @param <Produces>
 */
public class SingleClassFactory<Produces> extends ClassFactory<Produces> {

  public SingleClassFactory(DependencyResolver<Class<? extends Produces>, Produces> resolver, Class<Produces> c, Builder<Class<? extends Produces>, Produces> builder) {
    super(resolver);
    register(c, builder);
  }

  public SingleClassFactory(DependencyResolver<Class<? extends Produces>, Produces> resolver, Class<Produces> c) {
    super(resolver);
    register(c, new ClassBuilder<Produces>(c));
  }

}
