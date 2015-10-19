package ankh.factory;

import java.util.HashMap;
import ankh.builder.Builder;
import ankh.builder.ClassBuilder;
import ankh.exceptions.UnknownFactoryProductException;
import ankh.registrar.FactoryRegistrar;
import ankh.registrar.SingleClassFactoryRegistrar;
import ankh.resolver.DependencyResolver;

public class ClassFactory<P> extends AbstractFactory<Class<? extends P>, P> {

  public ClassFactory(DependencyResolver<Class<? extends P>, P> resolver) {
    super(resolver);
  }

  @Override
  public Builder<Class<? extends P>, P> register(Class<? extends P> identifier) {
    return register(identifier, new ClassBuilder<>(identifier));
  }

  @Override
  protected <R> R pick(HashMap<Class<? extends P>, R> map, Class<? extends P> id) throws UnknownFactoryProductException {
    R picked = map.get(id);

    if (picked != null)
      return picked;

    for (Class<? extends P> c : map.keySet())
      if (id.isAssignableFrom(c))
        return map.get(c);

    throw new UnknownFactoryProductException(id);
  }

  @SuppressWarnings("unchecked")
  public <T, C extends Class<? extends T>> FactoryRegistrar<ClassFactory<?>> registerClass(C c) {
    return new SingleClassFactoryRegistrar(resolver, c);
  }

  @SuppressWarnings("unchecked")
  public <T> FactoryRegistrar<ClassFactory<?>> registerClass(Class<? extends T> c, Builder<Class<? extends T>, T> builder) {
    return new SingleClassFactoryRegistrar(resolver, c, builder);
  }

}

