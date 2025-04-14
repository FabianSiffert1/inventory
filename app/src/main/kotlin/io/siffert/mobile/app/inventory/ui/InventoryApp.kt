package io.siffert.mobile.app.inventory.ui

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.siffert.mobile.app.inventory.core.designsystem.component.InventoryNavigationSuiteScaffold

@Composable
fun InventoryApp(
    appState: InventoryAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    /*
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.FOR_YOU

    NiaBackground(modifier = modifier) {
        NiaGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            // If user is not connected to the internet show a snack bar to inform them.
            val notConnectedMessage = stringResource(R.string.not_connected)
            LaunchedEffect(isOffline) {
                if (isOffline) {
                    snackbarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = Indefinite,
                    )
                }
            }
     */

    InventoryApp(appState = appState, windowAdaptiveInfo = windowAdaptiveInfo)
}


@Composable
internal fun InventoryApp(appState: InventoryAppState,
                          windowAdaptiveInfo: WindowAdaptiveInfo) {
    //todo: use appstate to iterate over topleveldestinations
    val foo = appState
    InventoryNavigationSuiteScaffold(
        navigationSuiteItems = { -> },
        windowAdaptiveInfo = windowAdaptiveInfo,
    )
    {
    }
}
