import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback,
    AfterAllCallback {

  private static final Logger LOG = Logger.getLogger(TimingExtension.class.getName());

  public void beforeTestExecution(TestExtensionContext context) throws Exception {
    getStore(context).put(context.getTestMethod().get(), System.currentTimeMillis());
  }

  public void afterTestExecution(TestExtensionContext context) throws Exception {
    Method testMethod = context.getTestMethod().get();
    long start = getStore(context).remove(testMethod, long.class);
    long duration = System.currentTimeMillis() - start;

    LOG.info(() -> String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
  }

  public void afterAll(ContainerExtensionContext context) throws Exception {

  }

  private Store getStore(ExtensionContext context) {
    return context.getStore(Namespace.create(getClass(), context));
  }

}
