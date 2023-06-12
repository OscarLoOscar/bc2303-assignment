###Bug : 
Description:

The dependencies of some of the beans in the application context form a cycle:

   channelController (field com.codewave.project.projectcryptochannel.service.ChannelService com.codewave.project.projectcryptochannel.controller.impl.ChannelController.channelService)
┌─────┐
|  channelServiceImpl (field com.codewave.project.projectcryptochannel.service.ChannelService com.codewave.project.projectcryptochannel.service.impl.ChannelServiceImpl.channelService)
└─────┘


Action:

Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.


####solution : 

pring Boot的Service层是应用程序中的业务逻辑层。它们与数据存储层（如Repository）和表示层（如Controller）相对独立，因此可以更轻松地更改和测试代码。

通常，Service层处理与业务相关的操作，例如数据验证，数据处理和数据转换。它们还通常调用Repository层以获取数据并将处理后的数据返回给Controller层。