import SwiftUI
import FeatureSplashResources

public struct SplashPage: View {

    @State
    private var scale: CGFloat = 0

    var onFinished: () -> Void

    public init(onFinished: @escaping () -> Void) {
        self.onFinished = onFinished
    }

    public var body: some View {
        Text(LocalizedStringKey(SplashResources().strings.logoContentDescription().resourceId))
                .padding()
                .scaleEffect(scale)
                .onAppear {
                    withAnimation(.spring(response: 0.4, dampingFraction: 0.1)) {
                        scale = 1
                    }
                    DispatchQueue.main.asyncAfter(deadline: .now() + 4) {
                        withAnimation {
                            onFinished()
                        }
                    }
                }
    }
}
