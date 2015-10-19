package ankh.factory;

import java.util.HashMap;
import ankh.builder.Builder;
import ankh.builder.ClassBuilder;
import ankh.exceptions.UnknownFactoryProductException;
import ankh.resolver.DependencyResolver;

public class ClassFactory<P> extends AbstractFactory<Class<? extends P>, P> {

  public ClassFactory(DependencyResolver<Class<? extends P>, P> resolver) {
    super(resolver);
  }

  @Override
  public Builder<Class<? extends P>, P> register(Class<? extends P> identifier) {
    return register(identifier, new ClassBuilder<P>(identifier));
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

}

