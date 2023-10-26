import SwiftUI
import FeatureRoot

public class ComponentHolder<T> {
    public let lifecycle: LifecycleRegistry
    public let component: T

    public init(factory: (ComponentContext) -> T) {
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let component = factory(DefaultComponentContext(lifecycle: lifecycle))
        self.lifecycle = lifecycle
        self.component = component

        lifecycle.onCreate()
    }

    deinit {
        lifecycle.onDestroy()
    }
}
