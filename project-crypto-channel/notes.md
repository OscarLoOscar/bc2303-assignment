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
