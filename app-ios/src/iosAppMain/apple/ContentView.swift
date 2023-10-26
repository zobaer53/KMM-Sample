import SwiftUI
import FeatureRoot

public struct ContentView: View {

    @State
    private var componentHolder = ComponentHolder {
        RootComponent(componentContext: $0)
    }

    public init() {
    }

    public var body: some View {
        RootContent(componentHolder.component)
                .onAppear {
                    LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle)
                }
                .onDisappear {
                    LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle)
                }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
