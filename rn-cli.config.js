const { getDefaultConfig } = require('metro-config');

module.exports = (async () => {
  const {
    resolver: { sourceExts },
  } = await getDefaultConfig();
  return {
    transformer: {
      assetPlugins: ['expo-asset/tools/hashAssetFiles'],
      babelTransformerPath: require.resolve('./vueTransformerPlugin.js'),
    },
    resolver: {
      sourceExts: [...sourceExts, 'vue'],
    },
  };
})();
