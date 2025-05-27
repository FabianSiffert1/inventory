@file:OptIn(ExperimentalUuidApi::class)

package io.siffert.mobile.app.feature.assets.io.siffert.mobile.app.feature.assets.components

import io.siffert.mobile.app.model.data.Asset
import io.siffert.mobile.app.model.data.AssetClass
import io.siffert.mobile.app.model.data.Currency
import io.siffert.mobile.app.model.data.PriceHistoryEntry
import io.siffert.mobile.app.model.data.SaleEntry
import kotlin.time.Duration.Companion.days
import kotlin.uuid.ExperimentalUuidApi
import kotlinx.datetime.Clock

private val assetid1 = "assetid1"
private val assetid2 = "assetid2"
private val assetid3 = "assetid3"
private val assetid4 = "assetid4"

private val priceHistoryEntryId1 = "priceHistoryEntryId1"
private val priceHistoryEntryId2 = "priceHistoryEntryId2"
private val priceHistoryEntryId3 = "priceHistoryEntryId3"
private val priceHistoryEntryId4 = "priceHistoryEntryId4"
private val priceHistoryEntryId5 = "priceHistoryEntryId5"

private val saleEntryId1 = "saleEntryId1"

val exampleAssetList =
    listOf(
        Asset(
            id = assetid1,
            name =
                "Real Asset with a very long name that is too long for a single line and should be truncated at some point. looooooooooooooooooooooooooooong",
            assetGroupId = null,
            assetClass = AssetClass.REAL_ASSET,
            fees = 0.10,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId1,
                        assetId = assetid1,
                        value = 1.12312312312321321321312,
                        timestamp = Clock.System.now(),
                    )
                ),
            saleInfo = null,
            currency = Currency.EUR,
            url = "https://google.com",
            userNotes = "userNotes1",
        ),
        Asset(
            id = assetid2,
            name = "Security Asset",
            assetGroupId = null,
            assetClass = AssetClass.SECURITY,
            fees = 5.012312312312310,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId2,
                        assetId = assetid2,
                        value = 1.30,
                        timestamp = Clock.System.now(),
                    )
                ),
            saleInfo = null,
            currency = Currency.EUR,
            url = null,
            userNotes =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur a bibendum est, et efficitur nunc. Morbi ut iaculis lorem, non accumsan tortor. Maecenas sollicitudin quam magna, et scelerisque nulla gravida id. Aliquam posuere lacus eu odio interdum fringilla condimentum et arcu. Donec ut dictum risus. Vestibulum non odio non dolor malesuada maximus. Nullam hendrerit nulla nec mi ultrices, eget lacinia diam pretium. Proin pretium quam in leo rutrum pretium. Morbi ullamcorper nunc id mattis volutpat. Morbi vitae neque sagittis, convallis eros vitae, tincidunt nibh. Fusce sodales erat non nisl convallis finibus. Morbi felis dolor, vehicula in felis in, vehicula vestibulum nunc. Nulla in blandit nisi. Phasellus consectetur lectus leo, a pharetra augue consequat quis.\n" +
                    "\n" +
                    "Interdum et malesuada fames ac ante ipsum primis in faucibus. Nunc malesuada lorem ut quam vulputate, eget venenatis lacus cursus. Donec leo augue, sollicitudin at ultrices sit amet, blandit sed orci. Duis varius sapien nec libero facilisis, a porttitor sem malesuada. Nullam sodales quis tortor nec mattis. Donec enim purus, commodo sit amet mi vitae, commodo laoreet mauris. Phasellus sapien ex, vestibulum in ex pulvinar, tempor cursus nulla. Vivamus est orci, tristique eget mattis sit amet, interdum eget leo. Pellentesque id aliquet dolor, et pellentesque turpis. Nunc pulvinar, lacus et porttitor ultrices, tortor diam ultricies tortor, vel semper nisl sem a turpis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, enim non sodales vehicula, elit tellus vestibulum erat, eu finibus est nisi sit amet neque. Aliquam ullamcorper consequat diam, id fermentum mi lacinia venenatis. Sed id arcu quam.\n" +
                    "\n" +
                    "Nulla facilisi. Fusce bibendum, lorem in fringilla ultrices, felis justo iaculis nibh, id fermentum quam massa id lacus. Etiam non imperdiet lacus. Curabitur id orci eu magna bibendum viverra. Pellentesque vehicula laoreet metus, et mollis nulla. Nullam interdum lacus nulla, sit amet mollis neque commodo ac. Sed quis tortor enim. Vivamus tincidunt, nibh at eleifend cursus, massa mi rutrum tellus, nec eleifend quam arcu a nisl.\n" +
                    "\n" +
                    "Donec hendrerit mauris lacus, sed pharetra magna tristique eu. Nunc volutpat gravida purus, et iaculis mauris. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras consequat, arcu in hendrerit consectetur, risus massa faucibus ante, eu tristique leo ex et dui. Vestibulum felis nibh, cursus sit amet nisi quis, finibus fringilla mauris. Etiam bibendum mi id diam lacinia, quis semper libero accumsan. Etiam non aliquet lacus, et gravida nulla.\n" +
                    "\n" +
                    "Cras fermentum pharetra ex, ac fermentum sem maximus ac. Fusce interdum eleifend dui, sit amet iaculis nisi feugiat id. Suspendisse potenti. Nullam odio tortor, placerat cursus tortor at, varius malesuada diam. Cras et tortor vulputate erat dapibus porta. Nam id hendrerit justo. Ut auctor nulla sed ipsum pulvinar suscipit. Etiam vulputate sem at odio ornare tincidunt. Integer dictum nibh ut tempus vulputate. Donec pellentesque semper accumsan. Aliquam erat volutpat. Aenean id mi augue. Maecenas egestas vulputate eros vitae pellentesque. Duis eu blandit leo. Integer semper quam et auctor venenatis.",
        ),
        Asset(
            id = assetid3,
            name = "Digital Asset",
            assetGroupId = null,
            assetClass = AssetClass.DIGITAL_ASSET,
            fees = 0.30,
            priceHistory =
                listOf(
                    PriceHistoryEntry(
                        id = priceHistoryEntryId3,
                        assetId = assetid3,
                        value = 1.40,
                        timestamp = Clock.System.now().minus(2.days),
                    ),
                    PriceHistoryEntry(
                        id = priceHistoryEntryId4,
                        assetId = assetid3,
                        value = 2000999991931823981293812839128398129881239812398.12,
                        timestamp = Clock.System.now(),
                    ),
                ),
            saleInfo =
                SaleEntry(
                    id = saleEntryId1,
                    assetId = assetid3,
                    value = 1.20,
                    timestamp = Clock.System.now(),
                ),
            currency = Currency.EUR,
            url = null,
            userNotes = null,
        ),
    )

val exampleAsset =
    Asset(
        id = assetid4,
        name = "Preview Asset",
        assetGroupId = null,
        assetClass = AssetClass.DIGITAL_ASSET,
        fees = 0.10,
        priceHistory =
            listOf(
                PriceHistoryEntry(
                    id = priceHistoryEntryId5,
                    assetId = assetid4,
                    value = 1.20,
                    timestamp = Clock.System.now(),
                )
            ),
        saleInfo = null,
        currency = Currency.EUR,
        url = "https://google.com",
        userNotes = "userNotes",
    )
